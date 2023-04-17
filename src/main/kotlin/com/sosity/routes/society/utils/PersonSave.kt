package com.sosity.routes.society.utils

import ch.qos.logback.core.util.FileUtil
import com.itextpdf.forms.PdfAcroForm
import com.itextpdf.kernel.pdf.PdfDocument
import com.itextpdf.kernel.pdf.PdfReader
import com.itextpdf.kernel.pdf.PdfWriter
import com.sosity.routes.society.tables.Person
import java.io.File

object PersonSave {

    fun pdf(person: Person) {
        val userHome = System.getProperty("user.home")
        val destination = File(userHome + "/Downloads/Societies/persons/" + person.name +".pdf")
        val source = File(object {}.javaClass.getResource("/static/files/Chitrakoot-Form.pdf")?.file.orEmpty())
        val isCreated = FileUtil.createMissingParentDirectories(destination)
        if (!isCreated) {
            throw Exception("${destination.path} is not created")
        }
        val pdfDoc = PdfDocument(PdfReader(source), PdfWriter(destination))
        val form = PdfAcroForm.getAcroForm(pdfDoc, true)
        // Being set as true, this parameter is responsible to generate an appearance Stream
        // while flattening for all form fields that don't have one. Generating appearances will
        // slow down form flattening, but otherwise Acrobat might render the pdf on its own rules.
        form.isGenerateAppearance = true
        form.getField("name").setValue(person.name, true)
        form.flattenFields()
        pdfDoc.close()
    }

}